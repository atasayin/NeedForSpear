package domain.strategy;

import domain.strategy.DestroyBehaviour;

public class GiftObstacleDestroyBehaviour implements DestroyBehaviour{

	public void destroy() {
		// TODO: Observer Pattern

		System.out.println("Gift is dropped");
	}

}
