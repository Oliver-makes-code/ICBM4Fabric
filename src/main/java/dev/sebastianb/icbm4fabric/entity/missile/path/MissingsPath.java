package dev.sebastianb.icbm4fabric.entity.missile.path;

import dev.sebastianb.icbm4fabric.utils.SebaUtils;
import dev.sebastianb.icbm4fabric.entity.missile.AbstractMissileProjectile;
import net.minecraft.entity.MovementType;

public class MissingsPath extends AbstractLaunchPath {
    double acc;

    double dX;
    double dY;
    double dZ;

    double dYaw;

    double arcModifier;

    public MissingsPath(AbstractMissileProjectile rocket) {
        super(rocket);
        if (!rocket.world.isClient)
            System.out.println("Rocket Travel Distance: " + rocket.getFinalLocation().getManhattanDistance(rocket.initialLocation));

        arcModifier = 1; // need to figure out a way to take in the angle wanted and distance traveled. Then calc how high it should be

        acc = -0.01 * arcModifier; // gravity acceleration. Should be negative

        dX = rocket.getFinalLocation().getX() - rocket.initialLocation.getX();
        dY = rocket.getFinalLocation().getY() - rocket.initialLocation.getY();
        dZ = rocket.getFinalLocation().getZ() - rocket.initialLocation.getZ();

        dYaw = Math.atan2(dX, dZ);
    }

    @Override
    public void updateMotion() {
        double groundDistTravelled = Math.sqrt(Math.pow(dX, 2) + Math.pow(dZ, 2)); // gets distance traveled horizontally

        double b = - acc * groundDistTravelled + (dY / groundDistTravelled);

        double temp1 = (1 / (2 * acc)) * SebaUtils.asinh(2 * acc * groundDistTravelled + b);
        double temp2 = (1 / (2 * acc)) * SebaUtils.asinh(b);

        double xfactor = groundDistTravelled / (temp1 - temp2); // * by any num to adjust speed.

        double hvel = xfactor * (1 / Math.sqrt(1 + (2 * acc * missile.timeSinceStage + b) * (2 * acc * missile.timeSinceStage + b)));

        missile.setVelocity(
                (Math.sin(dYaw) * hvel),
                (xfactor * ((2 * acc * missile.timeSinceStage + b) / Math.sqrt(1 + (2 * acc * missile.timeSinceStage + b) * (2 * acc * missile.timeSinceStage + b)))),
                (Math.cos(dYaw) * hvel)
        );
        missile.move(MovementType.SELF, missile.getVelocity());
    }
}
