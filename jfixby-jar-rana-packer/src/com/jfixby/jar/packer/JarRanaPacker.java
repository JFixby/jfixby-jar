
package com.jfixby.jar.packer;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.jfixby.cmns.api.assets.ID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.rana.api.pkg.StandardPackageFormats;
import com.jfixby.red.engine.core.resources.PackageUtils;
import com.jfixby.red.engine.core.resources.PackerSpecs;

public class JarRanaPacker {

	public static JarRanaPackerConfig newConfig () {
		return new JarRanaPackerConfig();
	}

	public static Collection<ID> listStoredClasses (final File inputJar) throws IOException {
		Debug.checkNull("inputJar", inputJar);
		inputJar.checkExists();
		final List<ID> result = Collections.newList();
		final FileInputStream is = inputJar.newInputStream();
		is.open();
		try {
			final InputStream jis = is.toJavaInputStream();
			final ZipInputStream zip = new ZipInputStream(jis);
			for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
				if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
					// This ZipEntry represents a class. Now, what class does it represent?
					final String classNameString = entry.getName().replace('/', '.'); // including ".class"
					if (classNameString.contains("$")) {
						continue;
					}
// classNames.add(className.substring(0, className.length() - ".class".length()));
					final ID className = Names.newID(classNameString).parent();
					result.add(className);
				}
			}
		} catch (final IOException io) {
			throw new IOException(io);
		} finally {
			is.close();
		}
		return result;
	}

	public static void pack (final JarRanaPackerConfig cfg) throws IOException {
		final File targetTank = cfg.getOutputTank();
		targetTank.makeFolder();
		targetTank.checkIsFolder();
		final Collection<File> jarsToPack = cfg.getInputJars();
		for (final File jar : jarsToPack) {
			pack(jar, targetTank);
		}
	}

	public static void pack (final File inputJar, final File targetTank) throws IOException {

// final File input_folder = inputJar.parent();

		final Collection<ID> classes = JarRanaPacker.listStoredClasses(inputJar);

		final String id_string = inputJar.getName();

		final File package_folder = targetTank.child(id_string);

		final PackerSpecs specs = new PackerSpecs();
		specs.setPackageFolder(package_folder);

// final ChildrenList files = input_folder.listDirectChildren();
// final File root_file = input_folder.child(specs.getRootFileName());
		specs.addPackedFile(inputJar);

		specs.setRootFileName(inputJar.getName());

		specs.setPackedAssets(classes);

		final List<ID> required = Collections.newList();
		specs.setRequiredAssets(required);

		specs.setPackageFormat(StandardPackageFormats.Java.Jar);
		specs.setVersion("1.0");

		PackageUtils.pack(specs);

	}

}
