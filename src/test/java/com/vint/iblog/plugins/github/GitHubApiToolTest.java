package com.vint.iblog.plugins.github;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * Created by Vin on 14-4-27.
 */
public class GitHubApiToolTest {
    @Test
    public void testFormatDirUrl() throws Exception {

        MatcherAssert.assertThat(GitHubApiTool.formatDefaultRefUrl("vintsie", "notebook", "contents"),
                new IsEqual<String>("https://api.github.com/repos/vintsie/notebook/contents"));
//        Assert.assertSame("https://api.github.com/repos/vintsie/notebook/contents",
//                GitHubApiTool.formatDirUrl("vintsie", "notebook", "contents"));
    }



    @Test
    public void testPullFileContent() throws Exception {
        JSONObject json = GitHubApiTool.pullFile(new String[]{"vintsie", "notebook", "_2014/_2014_01_03_项目奖又被忽悠了.md"});
        String content = json.getString("content");
        System.out.println(content);
    }
}
