package com.ypt.love.wss.shain.camera.interf;

public interface OnCameraStatusListener {
	// 相机拍照结束事件
	void onCameraStopped(byte[] data);

	// 拍摄时自动对焦事件
	void onAutoFocus(boolean success);
}
