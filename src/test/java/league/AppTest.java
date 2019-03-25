package league;

import org.junit.Test;

import java.io.IOException;

public class AppTest {

    @Test
    public void shouldRunWithValidFileName() throws Exception {
        App.main(new String[]{ClassLoader.getSystemResource("input-sample.txt").getPath()});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectMissingFileName() throws Exception {
        App.main(new String[]{});
    }

    @Test(expected = IOException.class)
    public void shouldRejectInvalidFileName() throws Exception {
        App.main(new String[]{"badfilename"});
    }

}


