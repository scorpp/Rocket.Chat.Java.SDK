package RocketChatAPI.RocketChatTest;

import RocketChatAPI.RocketChatTest.ChatParent.RocketChatParent;
import com.rocketchat.common.data.model.ErrorObject;
import com.rocketchat.common.listener.SimpleListener;
import com.rocketchat.core.model.TokenObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.timeout;

/**
 * Created by sachin on 3/8/17.
 */
public class LogoutTest extends RocketChatParent {

    String username = "testuserrocks";
    String password = "testuserrocks";

    @Mock
    SimpleListener listener;

    @Captor
    ArgumentCaptor<Boolean> successArgumentCaptor;

    @Captor
    ArgumentCaptor<ErrorObject> errorArgumentCaptor;

    @Before
    public void setUp() {
        super.setUpBefore(true);
    }

    @Override
    public void onConnect(String sessionID) {
        System.out.println("Connected successfully");
        api.login(username, password, this);
    }

    @Override
    public void onLogin(TokenObject token, ErrorObject error) {
        api.logout(listener);
    }

    @Test
    public void logoutTest() {
        Mockito.verify(listener, timeout(12000).atLeastOnce()).callback(successArgumentCaptor.capture(), errorArgumentCaptor.capture());
        Assert.assertNotNull(successArgumentCaptor.getValue());
        Assert.assertNull(errorArgumentCaptor.getValue());
    }

}