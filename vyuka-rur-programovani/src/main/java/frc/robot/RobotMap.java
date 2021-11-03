package frc.robot;

import edu.wpi.first.wpilibj.XboxController; //importuje tridu XboxController z WPI knihovny
import edu.wpi.first.wpilibj.buttons.JoystickButton; //importuje tridu JoystickButton ze slozky buttons z WPI knihovny
import edu.wpi.first.wpilibj.buttons.Button; //importuje tridu Button ze slozky buttons z WPI knihovny
import edu.wpi.first.wpilibj.GenericHID; //importuje tridu GenericHid z WPI knihovny
import edu.wpi.first.wpilibj.Talon; //importuje tridu Talon z WPI knihovny
import edu.wpi.first.wpilibj.SpeedControllerGroup; //importuje tridu SpeedContrellerGroup z WPI knihovny
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //importuje tridu DifferentialDrive z WPI knihovny
import edu.wpi.first.wpilibj.DoubleSolenoid; //importuje tridu DoubleSolenoid z WPI knihovny
import edu.wpi.first.wpilibj.Compressor; //importuje tridu Compressor z WPI knihovny

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; //importuje tridu WPI_TalonSRX z externi knihovny (viz Teams)

public class RobotMap {
    
    public RobotMap() { //konstruktor pro tridu RobotMap

    }
    
    //program pro motor controllery

    final Talon leftFront = new Talon(0); //vytvori novy objekt motor controlleru na PWM portu 0, na ktery se muzeme odkazat v nasledujicich definicich a funcich.
    final Talon rightFront = new Talon(1); //vytvori novy objekt motor controlleru na PWM portu 1, na ktery se muzeme odkazat v nasledujicich definicich a funcich.
    final Talon leftRear = new Talon(2); //vytvori novy objekt motor controlleru na PWM portu 2, na ktery se muzeme odkazat v nasledujicich definicich a funcich.
    final Talon rightRear = new Talon(3); //vytvori novy objekt motor controlleru na PWM portu 3, na ktery se muzeme odkazat v nasledujicich definicich a funcich.

    final WPI_TalonSRX intakeTalon = new WPI_TalonSRX(0); //vytvori novy objekt motor controlleru na CAN ID 0

    final SpeedControllerGroup leftTalons = new SpeedControllerGroup(leftFront, leftRear); //vytvori novy objekt sjednocujici objekty leftFront a leftRear, na ktery se muzeme odkazat v nasledujicich definicich a funkcich.
    final SpeedControllerGroup rightTalons = new SpeedControllerGroup(rightFront, rightRear); //vytvori novy objekt sjednocujici objekty rightFront a rightRear, na ktery se muzeme odkazat v nasledujicich definicich a funkcich.

    final DifferentialDrive drive = new DifferentialDrive(leftTalons, rightTalons); //vytvori novy objekt podvozku sjednocujici objekty leftTalons a rightTalons, na ktery se muzeme odkazat v nasledujicich funkcich.

    //program pro pneumatiku
    
    final DoubleSolenoid intakeShift = new DoubleSolenoid(0, 1); //vytvori novy objekt double solenoidu na portech PCM 0 a 1

    final Compressor compressor = new Compressor(0); //vytvori novy objekt typu Compressor na PCM portu 0, jakmile se jedno vytvori, automaticky se zapina a vypina

    //program pro ovladac

    final XboxController controller = new XboxController(0); //vytvori novy objekt ovladace, na ktery se muzeme odkazat v nasledujicich funkcich
    final Button buttonA = new JoystickButton(controller, 0); //vytvori novy objekt tlacitka na vyse definovanem ovladaci, diky kteremu muzeme sledovat, zda je toto tlacitko zmacknute
    final Button buttonB = new JoystickButton(controller, 1); //vytvori novy objekt tlacitka na vyse definovanem ovladaci, diky kteremu muzeme sledovat, zda je toto tlacitko zmacknute
    final Button buttonX = new JoystickButton(controller, 2); //vytvori novy objekt tlacitka na vyse definovanem ovladaci, diky kteremu muzeme sledovat, zda je toto tlacitko zmacknute
    final Button buttonY = new JoystickButton(controller, 3); //vytvori novy objekt tlacitka na vyse definovanem ovladaci, diky kteremu muzeme sledovat, zda je toto tlacitko zmacknute
    final Button leftBumper = new JoystickButton(controller, 5); //vytvori novy objekt tlacitka na vyse definovanem ovladaci
    final Button rightBumper = new JoystickButton(controller, 6); //vytvori novy objekt tlacitka na vyse definovanem ovladaci
    final Button leftStick = new JoystickButton(controller, 9); //vytvori novy objekt tlacitka na vyse definovanem ovladaci
    final Button rightStick = new JoystickButton(controller, 10); //vytvori novy objekt tlacita na vyse definovanem ovladaci

    /**
     * Funkce pro filtrovani malych hodnot (< 0.2)
     * @param input double ktery by mel byt porovnan s hranicni hodnotou.
     * @return dobule input pokud je vetsi nez hranicni hodnota, jinak 0.
     */
    public double deadzone (double input) {
        if(Math.abs(input) > 0.2){ return input; } //jednoradkova if struktura, pokud je hodnota promenne input vetsi nez 0.2, je vracena
        else { return 0; } //jednoradkova else struktura, pokud neplati podminka v if-strukture, vrati 0
    }

    /**
     * Funkce pro zjistovani hodnoty y osy na levem joysticku.
     * @return hodnota y leveho joysticku oriznuta o male hodnoty.
     */
    public double getLeftY() { return -deadzone(controller.getY(GenericHID.Hand.kLeft)); } //jednoradkova funkce, ktera vraci soucasnou hodnotu leveho joysticku na ose Y

    /**
     * Funkce pro zjistovani hodnoty x osy na levem joysticku.
     * @return hodnota x leveho joysticku oriznuta o male hodnoty.
     */
    public double getLeftX() { return deadzone(controller.getX(GenericHID.Hand.kLeft)); } //jednoradkova funkce, ktera vraci soucasnou hodnotu leveho joysticku na ose X

    /**
     * Funkce pro zjistovani hodnoty y osy na pravem joysticku.
     * @return hodnota y praveho joysticku oriznuta o male hodnoty.
     */
    public double getRightY() { return -deadzone(controller.getY(GenericHID.Hand.kRight)); } //jednoradkova funkce, ktera vraci soucasnou hodnotu praveho joysticku na ose Y

    /**
     * Funkce pro zjistovani hodnoty x osy na pravem joysticku.
     * @return hodnota x praveho joysticku oriznuta o male hodnoty.
     */
    public double getRightX() { return deadzone(controller.getX(GenericHID.Hand.kRight)); } //jednoradkova funkce, ktera vraci soucasnou hodnotu praveho joysticku na ose X

    /**
     * Funkce pro zjistovani hodnoty triggeru na ovladaci.
     * @return hodnota mezi 0 a 1 pro pravy trigger a hodnota mezi 0 a -1 pro levy trigger.
     */
    public double getTriggers() {
        if(controller.getTriggerAxis(GenericHID.Hand.kRight) > 0) {
            return deadzone(controller.getTriggerAxis(GenericHID.Hand.kRight));
        } else if(controller.getTriggerAxis(GenericHID.Hand.kLeft) < 0) {
            return -deadzone(controller.getTriggerAxis(GenericHID.Hand.kLeft));
        } else {
            return 0.0;
        }
    }
}