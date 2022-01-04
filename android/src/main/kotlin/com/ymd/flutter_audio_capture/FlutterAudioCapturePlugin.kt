package com.ymd.flutter_audio_capture

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterAudioCapturePlugin */
public class FlutterAudioCapturePlugin: FlutterPlugin, MethodCallHandler {
    private val audioCaptureStreamHandler: AudioCaptureStreamHandler = AudioCaptureStreamHandler()

  private var eventChannel: EventChannel? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val messenger = flutterPluginBinding.getBinaryMessenger()
    eventChannel = EventChannel(messenger, this.audioCaptureStreamHandler.eventChannelName)
    eventChannel?.setStreamHandler(this.audioCaptureStreamHandler)
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    eventChannel?.setStreamHandler(null)
  }
}
