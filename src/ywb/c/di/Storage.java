/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ywb.c.di;

public class Storage<T> {

	interface Strategy {

		void _setItem(String key, String val);

		String _getItem(String key);

		boolean _contains(String key);

		void _removeItem(String key);

		void _clear();

		int _length();
	}

	private Strategy strategy;

	public void init(Strategy strategy) {
		this.strategy = strategy;
	}

	public int length() {
		return strategy._length();
	}

	public void clear() {
		strategy._clear();
	}

	public void removeItem(String key) {
		strategy._removeItem(key);
	}

	public String getItem(String key) {
		return strategy._getItem(key);
	}

	public boolean hasKey(String key) {
		return strategy._contains(key);
	}

	public void setItem(String key, String val) {
		strategy._setItem(key, val);
	}

	// ===============================================================
	// implementations
	// ===============================================================

	/**
	 * JavaScript implementation
	 */
	public static class JsStorageStrategy implements Strategy {

		public native void _setItem(String key, String val) /*-{
			var _store = $wnd.localStorage;
			_store.removeItem(key);
			_store.setItem(key, val);
		}-*/;

		public native String _getItem(String key) /*-{
			var _store = $wnd.localStorage;
			return _store.getItem(key);
		}-*/;

		public native boolean _contains(String key) /*-{
			var _store = $wnd.localStorage;
			return (_store.getItem(key) != null);
		}-*/;

		public native void _removeItem(String key) /*-{
			var _store = $wnd.localStorage;
			_store.removeItem(key);
		}-*/;

		public native void _clear() /*-{
			var _store = $wnd.localStorage;
			_store.clear();
		}-*/;

		public native int _length() /*-{
			var _store = $wnd.localStorage;
			return _store.length;
		}-*/;
	}

}
