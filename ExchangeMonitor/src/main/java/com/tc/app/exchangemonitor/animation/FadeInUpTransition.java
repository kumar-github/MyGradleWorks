/*
    Animation For JavaFX.
 */

package com.tc.app.exchangemonitor.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeInUpTransition extends configAnimasi {
	/**
	 * Create new FadeInUpTransition
	 * 
	 * @param node The node to affect
	 */
	public FadeInUpTransition(final Node node) {
		super(
				node,
				TimelineBuilder.create()
				.keyFrames(
						new KeyFrame(Duration.millis(0),    
								new KeyValue(node.opacityProperty(), 0, WEB_EASE),
								new KeyValue(node.translateYProperty(), 20, WEB_EASE)
								),
						new KeyFrame(Duration.millis(500),    
								new KeyValue(node.opacityProperty(), 1, WEB_EASE),
								new KeyValue(node.translateYProperty(), 0, WEB_EASE)
								)
						)
				.build()
				);
		setCycleDuration(Duration.seconds(1));
		setDelay(Duration.seconds(0));
		node.toFront();
	}
}
