package com.wayneleo.test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Test01 {
    private static EventBus bus = new EventBus();
    static {
        bus.register( new EventHandler() );
    }

    public static void main( String[] args ) {
        PeopleDomainModel domainModel = new PeopleDomainModel( "Willianm" );
        bus.post( new RunningEvent( domainModel ) );
        bus.post( new JumpingEvent( domainModel ) );
    }

    public static class PeopleDomainModel {
        private String name;

        public PeopleDomainModel( String name ) {
            this.name = name;
        }
    }

    public static class RunningEvent {
        private PeopleDomainModel domainModel;

        public RunningEvent( PeopleDomainModel domainModel ) {
            this.domainModel = domainModel;
        }
    }

    public static class JumpingEvent {
        private PeopleDomainModel domainModel;

        public JumpingEvent( PeopleDomainModel domainModel ) {
            this.domainModel = domainModel;
        }
    }

    public static class EventHandler {
        @Subscribe
        public void handleRunning( RunningEvent event ) {
            System.out.println( event.domainModel.name + " is running" );
        }

        @Subscribe
        public void handleJumping( JumpingEvent event ) {
            System.out.println( event.domainModel.name + " is jumping" );
        }
    }
}
