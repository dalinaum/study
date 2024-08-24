import 'package:flutter_application/pb/rpc_chat.pb.dart';
import 'package:flutter_application/services/auth.dart';
import 'package:flutter_application/services/grpc_services.dart';
import 'package:grpc/grpc.dart';

class ChatService {
  static Future<List<Message>> getMessages(String username) async {
    final res = await GrpcService.client.getAllMessage(
      GetAllMessageRequest(
        receiver: username,
      ),
      options: CallOptions(
        metadata: {'authorization': 'bearer ${AuthService.authToken}'},
      ),
    );
    return res.messages;
  }
}
