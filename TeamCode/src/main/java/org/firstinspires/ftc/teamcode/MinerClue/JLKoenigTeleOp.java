package org.firstinspires.ftc.teamcode.MinerClue;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;

/**
 * Created by JL on 9/25/2017.
 */
@TeleOp(name = "Sample", group = "TeleOp")
public class JLKoenigTeleOp extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor rightMotorTest;
    private DcMotor leftMotorTest;
    private DcMotor elevatorMotorTest;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    double elevatorPower = 1;
    boolean elevatorFlag = false;
    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        rightMotorTest = hardwareMap.get(DcMotor.class, "Rmotor");
        rightMotorTest.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotorTest = hardwareMap.get(DcMotor.class, "Lmotor");
        elevatorMotorTest = hardwareMap.get(DcMotor.class, "elevator");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        while (opModeIsActive()) {

            tgtPower = -this.gamepad1.left_stick_y;
            leftMotorTest.setPower(tgtPower);
            tgtPower = -this.gamepad1.right_stick_y;
            rightMotorTest.setPower(tgtPower);
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Right Motor Power", rightMotorTest.getPower());
            telemetry.addData("Left Motor Power", leftMotorTest.getPower());

            if(this.gamepad1.left_trigger != 0 && elevatorFlag && elevatorPower < 1) {
                elevatorPower += 0.10;
                elevatorPower = Math.round(elevatorPower * 10)/10.0;
                elevatorFlag = false;
            } else if(this.gamepad1.right_trigger != 0 && elevatorFlag && elevatorPower > 0) {
                elevatorPower -= 0.1;
                elevatorPower = Math.round(elevatorPower * 10)/10.0;
                elevatorFlag = false;
            } else if(this.gamepad1.left_trigger == 0 && this.gamepad1.right_trigger == 0)
                elevatorFlag = true;

            telemetry.addData("Elevator Power", elevatorPower);

            if(this.gamepad1.a)
                elevatorMotorTest.setPower(elevatorPower);
            else
                elevatorMotorTest.setPower(0);
            if (this.gamepad1.b)
                elevatorMotorTest.setPower (-elevatorPower);
            else
                elevatorMotorTest.setPower (0);


            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }



//    //Defining motors
//    DcMotor rightMotor;
//    DcMotor leftMotor;
//
//
//
//    //Motor powers
//    double rightPower;
//    double leftPower;
//
//
//    @Override
//    public void init() {
//        rightMotor = hardwareMap.dcMotor.get("Rmoter");
//        leftMotor = hardwareMap.dcMotor.get("Lmotor");
//
//        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    @Override
//    public void loop() {
//        rightPower = -gamepad1.right_stick_y;
//        leftPower = -gamepad1.left_stick_y;
//
//        rightMotor.setPower(rightPower);
//        leftMotor.setPower(leftPower);
//
//        double tgtPower = 0;
//        while (opModeIsActive()) {
//            tgtPower = -this.gamepad1.left_stick_y;
//            telemetry.addData("Target Power", tgtPower);
//            telemetry.addData("Motor Power", motorTest.getPower());
//            telemetry.addData("Status", "Running");
//            telemetry.update();
//        }
//
//    }
}
