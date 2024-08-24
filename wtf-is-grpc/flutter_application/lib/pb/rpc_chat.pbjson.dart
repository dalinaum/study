//
//  Generated code. Do not modify.
//  source: rpc_chat.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use messageDescriptor instead')
const Message$json = {
  '1': 'Message',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 9, '10': 'id'},
    {'1': 'sender', '3': 2, '4': 1, '5': 9, '10': 'sender'},
    {'1': 'receiver', '3': 3, '4': 1, '5': 9, '10': 'receiver'},
    {'1': 'message', '3': 4, '4': 1, '5': 9, '10': 'message'},
    {'1': 'created_at', '3': 5, '4': 1, '5': 11, '6': '.google.protobuf.Timestamp', '10': 'createdAt'},
  ],
};

/// Descriptor for `Message`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List messageDescriptor = $convert.base64Decode(
    'CgdNZXNzYWdlEg4KAmlkGAEgASgJUgJpZBIWCgZzZW5kZXIYAiABKAlSBnNlbmRlchIaCghyZW'
    'NlaXZlchgDIAEoCVIIcmVjZWl2ZXISGAoHbWVzc2FnZRgEIAEoCVIHbWVzc2FnZRI5CgpjcmVh'
    'dGVkX2F0GAUgASgLMhouZ29vZ2xlLnByb3RvYnVmLlRpbWVzdGFtcFIJY3JlYXRlZEF0');

@$core.Deprecated('Use sendMessageRequestDescriptor instead')
const SendMessageRequest$json = {
  '1': 'SendMessageRequest',
  '2': [
    {'1': 'message', '3': 1, '4': 1, '5': 9, '10': 'message'},
    {'1': 'receiver', '3': 2, '4': 1, '5': 9, '10': 'receiver'},
  ],
};

/// Descriptor for `SendMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List sendMessageRequestDescriptor = $convert.base64Decode(
    'ChJTZW5kTWVzc2FnZVJlcXVlc3QSGAoHbWVzc2FnZRgBIAEoCVIHbWVzc2FnZRIaCghyZWNlaX'
    'ZlchgCIAEoCVIIcmVjZWl2ZXI=');

@$core.Deprecated('Use getAllMessageRequestDescriptor instead')
const GetAllMessageRequest$json = {
  '1': 'GetAllMessageRequest',
  '2': [
    {'1': 'receiver', '3': 1, '4': 1, '5': 9, '10': 'receiver'},
  ],
};

/// Descriptor for `GetAllMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List getAllMessageRequestDescriptor = $convert.base64Decode(
    'ChRHZXRBbGxNZXNzYWdlUmVxdWVzdBIaCghyZWNlaXZlchgBIAEoCVIIcmVjZWl2ZXI=');

@$core.Deprecated('Use getAllMessageResponseDescriptor instead')
const GetAllMessageResponse$json = {
  '1': 'GetAllMessageResponse',
  '2': [
    {'1': 'messages', '3': 1, '4': 3, '5': 11, '6': '.pb.Message', '10': 'messages'},
  ],
};

/// Descriptor for `GetAllMessageResponse`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List getAllMessageResponseDescriptor = $convert.base64Decode(
    'ChVHZXRBbGxNZXNzYWdlUmVzcG9uc2USJwoIbWVzc2FnZXMYASADKAsyCy5wYi5NZXNzYWdlUg'
    'htZXNzYWdlcw==');

