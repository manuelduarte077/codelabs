import 'package:flutter/services.dart';

class DeviceBatteryChannel {
  static const MethodChannel _channel =
      MethodChannel('com.manuel.macos_battery/device_battery');

  static Future<double> getBatteryLevel() async {
    try {
      final double result = await _channel.invokeMethod('getBatteryLevel');
      return result;
    } on PlatformException {
      return 0.0;
    }
  }

  static Future<bool> isBatteryCharging() async {
    try {
      final bool result = await _channel.invokeMethod('isBatteryCharging');
      return result;
    } on PlatformException {
      return false;
    }
  }

  static Future<String> getBatteryTimeLeft() async {
    try {
      final String result = await _channel.invokeMethod('getBatteryTimeLeft');
      return result;
    } on PlatformException {
      return '';
    }
  }
}
