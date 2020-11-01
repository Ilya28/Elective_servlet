package org.elective.service;

import junit.framework.TestCase;
import org.junit.Assert;

public class WebPathsTest extends TestCase {

    public void testConvertNameToPath() {
        Assert.assertEquals("/jsp/main.jsp", WebPaths.nameToPath("main"));
    }

    public void testExtractFinalPath() {
    }

    public void testIsRedirect_Redirect() {
        Assert.assertTrue(WebPaths.isRedirect("redirect:/index"));
    }

    public void testIsRedirect_NoRedirect() {
        Assert.assertFalse(WebPaths.isRedirect("/registrations/find_all"));
    }

    public void testGetUrlFromRedirection() {
        Assert.assertEquals("/main", WebPaths.getUrlFromRedirection("redirect:/main"));
    }
}