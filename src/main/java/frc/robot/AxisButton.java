package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

public class AxisButton extends Button {
    private GenericHID source;
    private int id;

    public AxisButton(GenericHID inputDevice, int axisId) {
        source = inputDevice;
        this.id = axisId;
    }

    @Override
    public boolean get() {
        return source.getRawAxis(this.id) != 0;
    }
}
