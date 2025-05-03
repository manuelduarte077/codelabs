import 'package:flutter/material.dart';

import 'device_battery_channel.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  double _batteryLevel = 0.0;
  bool _isBatteryCharging = false;
  String _timeLeft = '0';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Battery App'),
      ),
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Battery level: $_batteryLevel%'),
                const SizedBox(height: 20),
                FilledButton.icon(
                  style: ButtonStyle(
                    backgroundColor: WidgetStateProperty.all(Colors.blue),
                  ),
                  onPressed: () async {
                    double batteryLevel =
                        await DeviceBatteryChannel.getBatteryLevel();
                    setState(() {
                      _batteryLevel = batteryLevel;
                    });
                  },
                  label: const Text('Get battery level'),
                  icon: const Icon(Icons.battery_full),
                ),
              ],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Is battery charging: $_isBatteryCharging'),
                const SizedBox(height: 20),
                FilledButton.icon(
                  style: ButtonStyle(
                    backgroundColor: WidgetStateProperty.all(Colors.green),
                  ),
                  onPressed: () async {
                    bool isBatteryCharging =
                        await DeviceBatteryChannel.isBatteryCharging();
                    setState(() {
                      _isBatteryCharging = isBatteryCharging;
                    });
                  },
                  label: const Text('Is battery charging?'),
                  icon: const Icon(Icons.battery_charging_full),
                ),
              ],
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text('Time left: $_timeLeft'),
                const SizedBox(height: 20),
                FilledButton.icon(
                  style: ButtonStyle(
                    backgroundColor: WidgetStateProperty.all(Colors.red),
                  ),
                  onPressed: () async {
                    String timeLeft =
                        await DeviceBatteryChannel.getBatteryTimeLeft();
                    setState(() {
                      _timeLeft = timeLeft;
                    });
                  },
                  label: const Text('Get battery time left'),
                  icon: const Icon(Icons.timer),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
