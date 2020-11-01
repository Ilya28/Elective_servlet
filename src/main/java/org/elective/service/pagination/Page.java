package org.elective.service.pagination;

import javax.servlet.http.HttpServletRequest;

public class Page {
    public static final String ATTRIBUTE_PAGE_HOLDER = "page";

    private int page;
    private int count;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1)
            page = 1;
        if (page > count)
            page = count;
        this.page = page;
    }

    public void setPage(HttpServletRequest request) {
        int extractedPage;
        try {
            extractedPage = Integer.parseInt(request.getParameter(ATTRIBUTE_PAGE_HOLDER));
        } catch (NumberFormatException e) {
            extractedPage = 1;
        }
        setPage(extractedPage);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 1)
            count = 1;
        this.count = count;
    }

    public Page(int page, int count) {
        this.page = page;
        this.count = count;
    }

    public Page() {
        this.page = 1;
        this.count = 1;
    }

    public Page(HttpServletRequest request, int count) {
        setCount(count);
        setPage(request);
    }
}
