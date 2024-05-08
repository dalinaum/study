import 'dart:io';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_application/pb/empty_request.pb.dart';
import 'package:flutter_application/pb/rpc_notifications.pb.dart';
import 'package:flutter_application/services/grpc_services.dart';
import 'package:flutter_background_service/flutter_background_service.dart';
import 'package:flutter_background_service_android/flutter_background_service_android.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:shared_preferences/shared_preferences.dart';

class NotificationServices {
  static String notificationChannelId = 'user-notification-channel';
  static int notificationId = 888;
  static String notificationService = "notification-service";

  // Initialize the background notification service.
  static Future<void> initializeService() async {
    final service = FlutterBackgroundService();

    AndroidNotificationChannel channel = AndroidNotificationChannel(
      notificationChannelId,
      notificationService,
      description: 'This channel is used for important notification',
      importance: Importance.high,
    );

    final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
        FlutterLocalNotificationsPlugin();
    if (Platform.isIOS || Platform.isAndroid) {
      await flutterLocalNotificationsPlugin.initialize(
        const InitializationSettings(
          iOS: DarwinInitializationSettings(),
          android: AndroidInitializationSettings('ic_bg_service_small'),
        ),
      );
    }

    await flutterLocalNotificationsPlugin
        .resolvePlatformSpecificImplementation<
            AndroidFlutterLocalNotificationsPlugin>()
        ?.createNotificationChannel(channel);
    await flutterLocalNotificationsPlugin
        .resolvePlatformSpecificImplementation<
            AndroidFlutterLocalNotificationsPlugin>()
        ?.requestPermission();

    await service.configure(
      iosConfiguration: IosConfiguration(
        autoStart: true,
        onForeground: onStart,
        onBackground: onIosBackground,
      ),
      androidConfiguration: AndroidConfiguration(
        onStart: onStart, // Function to execute when service starts.
        autoStart: true, // Automatically start the service.
        isForegroundMode: false,
        notificationChannelId:
            notificationChannelId, // Notification channel ID to use.
        autoStartOnBoot: true, // Automatically start on device boot.
      ),
    );
    service.startService();
  }

  // Function to execute when the service is running in the background on iOS.
  static Future<bool> onIosBackground(ServiceInstance service) async {
    WidgetsFlutterBinding.ensureInitialized();
    DartPluginRegistrant.ensureInitialized();

    SharedPreferences preferences = await SharedPreferences.getInstance();
    await preferences.reload();
    final log = preferences.getStringList('log') ?? <String>[];
    log.add(DateTime.now().toIso8601String());
    await preferences.setStringList('log', log);

    return true;
  }

  // Function to execute when the service starts (in the background or foreground).
  static Future<void> onStart(ServiceInstance service) async {
    DartPluginRegistrant.ensureInitialized();
    final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
        FlutterLocalNotificationsPlugin();

    if (service is AndroidServiceInstance) {
      service.on('setAsForeground').listen((event) {
        service.setAsForegroundService();
      });

      service.on('setAsBackground').listen((event) {
        service.setAsBackgroundService();
      });
    }
    service.on('stop_service').listen((event) {
      service.stopSelf();
    });

    if (service is AndroidServiceInstance) {
      final res = getNotification();
      res.listen((event) {
        flutterLocalNotificationsPlugin.show(
          notificationId,
          event.title,
          event.description,
          NotificationDetails(
            android: AndroidNotificationDetails(
              notificationChannelId,
              notificationService,
              icon: 'ic_bg_service_small',
              ongoing: false,
              autoCancel: true,
            ),
          ),
        );
      });
    }
  }

  // Stream that receives NotificationMessage from the gRPC service.
  static Stream<NotificationMessage> getNotification() async* {
    final request = EmptyRequestMessage();
    final responseStream = GrpcService.client.getNotifications(request);
    await for (var notification in responseStream) {
      // Yield each received NotificationMessage.
      yield notification;
    }
  }
}
