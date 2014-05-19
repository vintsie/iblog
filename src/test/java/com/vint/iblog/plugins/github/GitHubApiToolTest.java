package com.vint.iblog.plugins.github;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.json.JSONObject;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 *
 * Created by Vin on 14-4-27.
 */
public class GitHubApiToolTest {
    @Test
    public void testPullFileContent() throws Exception {
        JSONObject json = GitHubApiTool.pull(
                new String[]{"vintsie", "notebook", "_2014/Test_Code_highlight.md"});
        String content = json.getString("content");

        //VerbatimSerializer vs = new DefaultVerbatimSerializer();
        //Map<String, VerbatimSerializer> serializes = new HashMap<String, VerbatimSerializer>();
        //serializes.put("java", vs);

        String html = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS).markdownToHtml(content);
        System.out.println(html);
        System.out.println(content);
    }
}
