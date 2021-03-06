/*
 * ====================================================================
 * This software is subject to the terms of the Common Public License
 * Agreement, available at the following URL:
 *   http://www.opensource.org/licenses/cpl.html .
 * You must accept the terms of that agreement to use this software.
 * ====================================================================
 */
package com.eyeq.pivot4j.datasource;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.olap4j.OlapConnection;
import org.olap4j.OlapDataSource;

public abstract class AbstractOlapDataSource implements OlapDataSource {

	private PrintWriter logWriter;

	private int loginTimeout;

	/**
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() {
		return logWriter;
	}

	/**
	 * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter out) {
		this.logWriter = out;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}

	/**
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() {
		return loginTimeout;
	}

	/**
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int seconds) {
		this.loginTimeout = seconds;
	}

	/**
	 * @see org.olap4j.OlapDataSource#getConnection()
	 */
	public OlapConnection getConnection() throws SQLException {
		return createConnection(null, null);
	}

	/**
	 * @see org.olap4j.OlapDataSource#getConnection(java.lang.String,
	 *      java.lang.String)
	 */
	public OlapConnection getConnection(String username, String password)
			throws SQLException {
		return createConnection(username, password);
	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	protected abstract OlapConnection createConnection(String userName,
			String password) throws SQLException;

	/**
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return (T) this;
	}

	/**
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return iface.isAssignableFrom(getClass());
	}
}
