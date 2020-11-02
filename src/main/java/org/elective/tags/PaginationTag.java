package org.elective.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private int page;
    private int count;

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("<hr>");
            for (int i = 1; i <= count; i ++)
                if (i == page)
                    pageContext.getOut().print(" ="+i+"= ");
                else
                    pageContext.getOut().print("<a href=\"?page="+i+"\"> -"+i+"- </a>");
            pageContext.getOut().print("<hr>");
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }
}