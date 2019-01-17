package com.pheu.filter;

public class FilterResult {
	private boolean isNext;
	private IResponse response;

	FilterResult() {
	}

	public static class Builder {
		private boolean isNext;
		private IResponse response;

		public Builder() {
		}

		public Builder isNext(boolean isNext) {
			this.isNext = isNext;
			return this;
		}

		public Builder response(IResponse response) {
			this.response = response;
			return this;
		}

		public FilterResult build() {
			return new FilterResult(isNext, response);
		}
	}

	FilterResult(boolean isNext, IResponse response) {
		this.isNext = isNext;
		this.response = response;
	}

	public static Builder create() {
		return new Builder();
	}

	public static FilterResult pair(boolean isNext, IResponse response) {
		return new FilterResult(isNext, response);
	}

	public boolean isNext() {
		return isNext;
	}

	public IResponse getResponse() {
		return response;
	}
}
