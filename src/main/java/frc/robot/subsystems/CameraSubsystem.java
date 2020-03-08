package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
//    private final UsbCamera colorCamera;
    private UsbCamera driveCamera;
    private UsbCamera shootCamera;
    private VideoSink server;

    public CameraSubsystem() {
        driveCamera = CameraServer.getInstance().startAutomaticCapture();
        driveCamera.setFPS(15);
        driveCamera.setBrightness(50);
        driveCamera.setResolution(320, 240);
        System.out.println(driveCamera.getConfigJson());


        shootCamera = CameraServer.getInstance().startAutomaticCapture();
        shootCamera.setFPS(15);
        shootCamera.setBrightness(50);
        shootCamera.setResolution(320, 240);

//        colorCamera = CameraServer.getInstance().startAutomaticCapture();
//        colorCamera.setFPS(10);
//        colorCamera.setBrightness(50);
//        colorCamera.setResolution(160, 120);


        server = CameraServer.getInstance().getServer();
        shootCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
        driveCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
//        colorCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kForceClose);
    }

    public void useDriveCamera() {
        server.setSource(driveCamera);
    }

    public void useShooterCamera() {
        server.setSource(shootCamera);
    }
}