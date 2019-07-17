package TileGame.Entity;

public enum EntityCollision {
	HealFriend(), //Collision between bullet and other player
	AttackEnemy(), //Collision between bullet and enemy
	PlayerDies(), //Collision between enemy and player
	PickUpKey(), //Collision between player and pickup
	EntityCollision(), //Collision between anything else
	NoCollision();
}
