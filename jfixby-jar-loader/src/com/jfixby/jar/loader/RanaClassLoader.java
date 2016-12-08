
package com.jfixby.jar.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class RanaClassLoader extends ClassLoader {

	public RanaClassLoader () {
		this(RanaClassLoader.class.getClassLoader());
	}

	public RanaClassLoader (final ClassLoader parent) {
		super(parent);
		System.out.println("parent " + parent);
	}

	@Override
	public Class<?> loadClass (final String name) throws ClassNotFoundException {
		System.out.println("RanaClassLoader is loading " + name);
		return super.loadClass(name);
	}// loadClass

	@Override
	public synchronized Class<?> loadClass (final String name, final boolean resolve) throws ClassNotFoundException {
		System.out.println("RanaClassLoader is loading " + name + " with resolve = " + resolve);
		return super.loadClass(name, resolve);
	}

	@Override
	protected Class<?> findClass (final String name) throws ClassNotFoundException {
		System.out.println("RanaClassLoader findClass " + name);
		return super.findClass(name);
	}

	@Override
	protected URL findResource (final String name) {
		System.out.println("RanaClassLoader findResource " + name);
		return super.findResource(name);
	}

	@Override
	protected Enumeration<URL> findResources (final String name) throws IOException {
		System.out.println("RanaClassLoader findResources " + name);
		return super.findResources(name);
	}

	@Override
	protected Package getPackage (final String name) {
		System.out.println("RanaClassLoader getPackage " + name);
		return super.getPackage(name);
	}

	@Override
	public URL getResource (final String name) {
		System.out.println("RanaClassLoadergetResource " + name);
		return super.getResource(name);
	}

	@Override
	public InputStream getResourceAsStream (final String name) {
		System.out.println("RanaClassLoader getResourceAsStream " + name);
		return super.getResourceAsStream(name);
	}

	@Override
	public Enumeration<URL> getResources (final String name) throws IOException {
		System.out.println("RanaClassLoader getResources " + name);
		return super.getResources(name);
	}
}// RanaClassLoader
