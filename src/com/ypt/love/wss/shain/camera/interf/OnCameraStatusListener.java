package com.ypt.love.wss.shain.camera.interf;

public interface OnCameraStatusListener {
	// ������ս����¼�
	void onCameraStopped(byte[] data);

	// ����ʱ�Զ��Խ��¼�
	void onAutoFocus(boolean success);
}
