package com.hy.webTest.web;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class GsmbizControllerTest {
    private final GsmbizController gsmbizController;
    @Test
    public void test_gsmbizIssue() throws Exception {
        gsmbizController.gsmbizIssue();
    }
}
