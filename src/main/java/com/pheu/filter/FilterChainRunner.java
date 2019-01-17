package com.pheu.filter;

import java.util.Map;

public class FilterChainRunner {
	private IFilterChain chain = new FilterChainImpl();

	private FilterChainRunner() {
	}

	public static FilterChainRunner create() {
		return new FilterChainRunner();
	}

	public FilterChainRunner with(IFilter filter) {
		if (filter == null) {

			throw new FilterRuntimeException(FilterExceptionCode.FILTER_NULL.getStatus(),
					FilterExceptionCode.FILTER_NULL.getMessage());
		}
		chain.registerFilter(filter);
		return this;
	}

	public void run(IContext context) {
		chain.execute(context);
	}

	public IResponse run(Map<String, String> params) {
		return chain.execute(new ContextImpl(params));
	}
}
