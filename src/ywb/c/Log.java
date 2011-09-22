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
package ywb.c;

import java.util.logging.Logger;

// just one master logger. KISS
public class Log {

	static Logger log = Logger.getLogger("ywb");

	{
		// Logger.getLogger("").getHandlers()[0].
		// com.google.gwt.logging.Logging
	}

	public static void trace(String msg) {
		log.fine(msg);
	}

	public static void info(String msg) {
		log.info(msg);
	}

	public static void warn(String msg) {
		log.warning(msg);
	}

	public static void error(String msg) {
		log.severe(msg);
	}

	// public static void throwing(String sourceClass, String sourceMethod, Throwable failure) {
	// log.throwing(sourceClass, sourceMethod, failure);
	// }

}
