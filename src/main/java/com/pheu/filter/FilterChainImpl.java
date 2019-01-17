package com.pheu.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChainImpl implements IFilterChain {
	private List<IFilter> filters;

	public FilterChainImpl() {
		filters = new ArrayList<>();
	}

	public IResponse execute(IContext context) {
		for (IFilter filter : filters) {
			FilterResult resp = filter.doFilter(context);
			if (!resp.isNext()) {
				return resp.getResponse();
			}
		}

		throw new FilterRuntimeException(FilterExceptionCode.NO_CONTENT.getStatus(),
				FilterExceptionCode.NO_CONTENT.getMessage());
	}

	public void registerFilter(IFilter filter) {
		filters.add(filter);
	}
}
