package com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void perform(HttpServletRequest req, HttpServletResponse resp);

}
