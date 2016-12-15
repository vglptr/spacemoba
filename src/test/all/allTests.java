package test.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.server.GameServerTest;
import test.shared.LevelTest;
import test.shared.ShipTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LevelTest.class, ShipTest.class, GameServerTest.class })

public class allTests {

}
