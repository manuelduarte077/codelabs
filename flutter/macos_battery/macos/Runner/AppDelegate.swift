import Cocoa
import FlutterMacOS

@NSApplicationMain
class AppDelegate: FlutterAppDelegate {
    
    let channelName: String = "com.manuel.macos_battery/device_battery"
    
    override func applicationShouldTerminateAfterLastWindowClosed(_ sender: NSApplication) -> Bool {
        return true
    }
    
      override func applicationDidFinishLaunching(_ notification: Notification) {
          let controller : FlutterViewController = mainFlutterWindow?.contentViewController as! FlutterViewController
          let channel = FlutterMethodChannel.init(name: channelName, binaryMessenger: controller.engine.binaryMessenger)
          channel.setMethodCallHandler({ (_ call: FlutterMethodCall, _ result: FlutterResult) -> Void in
              if ("getBatteryLevel" == call.method) {
                  let internalFinder = InternalFinder()
                  if let internalBattery = internalFinder.getInternalBattery() {
                      result(internalBattery.charge)
                      return
                  }
                  result(0.0)
              } else if("isBatteryCharging" == call.method) {
                  let internalFinder = InternalFinder()
                  if let internalBattery = internalFinder.getInternalBattery() {
                      result(internalBattery.isCharging)
                      return
                  }
                  result(false)
              } else if("getBatteryTimeLeft" == call.method) {
                  let internalFinder = InternalFinder()
                  if let internalBattery = internalFinder.getInternalBattery() {
                      result(internalBattery.timeLeft)
                      return
                  }
                  result("")
              }else {
                  result(FlutterMethodNotImplemented)
              }
          });
      }

}
    
