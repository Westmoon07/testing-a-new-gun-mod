package co.uk.mrpineapple.toyguns.common.entity;

import co.uk.mrpineapple.toyguns.core.registry.ItemRegistry;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.mrcrayfish.guns.item.GunItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

/*
 * This class is for my dart projectile - of course you can just use normal ammunition, however, I've included this as it
 * looks cool with the toy guns.
 *
 * It extends the ProjectileEntity class from the Gun Mod - this means that it inherits everything from that class.
 * This means that we don't need to write all of the methods and logic again.
 */

/**
 * Author: Mr. Pineapple
 */
public class DartEntity extends ProjectileEntity {
    public DartEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public DartEntity(EntityType<? extends ProjectileEntity> entityType, World world, LivingEntity shooter, ItemStack weapon, GunItem item, Gun modifiedGun) {
        super(entityType, world, shooter, weapon, item, modifiedGun);
    }

    /*
     * This method is overwritten from the ProjectileEntity class.
     * I have called the super which means that it will do everything that is in the parent class + whatever logic we add.
     * In this case, I have it call a local method called onHit
     */
    @Override
    protected void onHitEntity(Entity entity, Vector3d hitVec, Vector3d startVec, Vector3d endVec, boolean headshot) {
        super.onHitEntity(entity, hitVec, startVec, endVec, headshot);
        this.onHit(hitVec.getX(), hitVec.getY(), hitVec.getZ());
    }

    /*
     * Pretty much like the onHitEntity, however, this time, we don't super, as I don't want the same logic to apply.
     * Bullet Holes being the main thing here.
     * Instead I call my onHit method like the onHitEntity above.
     */
    @Override
    protected void onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z) {
        this.onHit(x, y, z);
    }

    /*
     * This is our method, because it is being called whenever the bullet hits something, I can then apply some logic to it.
     * In this case, I get the world and add an entity - a new instance of the dart. This means the player can pick up the darts
     * and re-use them in their guns.
     */
    void onHit(double x, double y, double z) {
        world.addEntity(new ItemEntity(world, x, y, z, new ItemStack(ItemRegistry.DART.get())));
    }
}
