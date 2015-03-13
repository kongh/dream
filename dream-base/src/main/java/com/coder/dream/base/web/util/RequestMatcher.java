package com.coder.dream.base.web.util;

import javax.servlet.http.HttpServletRequest;

public interface RequestMatcher {

    /**
     * Decides whether the rule implemented by the strategy matches the supplied request.
     *
     * @param request the request to check for a match
     * @return true if the request matches, false otherwise
     */
    boolean matches(HttpServletRequest request);

}
