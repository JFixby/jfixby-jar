
package com.jfixby.jar.packer.test;

import com.jfixby.jar.packer.JarRanaPacker;
import com.jfixby.jar.packer.JarRanaPackerConfig;
import com.jfixby.scarabei.adopted.gdx.json.RedJson;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.file.ChildrenList;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.tool.eclipse.dep.EclipseProjectInfo;
import com.jfixby.tool.eclipse.dep.EclipseWorkSpaceSettings;

public class PackJarsToBank {
	public static final String WORKSPACE_FOLDER = "D:\\[DEV]\\[CODE]\\[WS-20]";

	public static void main (final String[] args) throws Throwable {
		DesktopSetup.deploy();
		Json.installComponent(new RedJson());

		final File workspace_folder = LocalFileSystem.newFile(WORKSPACE_FOLDER);
		final EclipseWorkSpaceSettings workspace_settings = EclipseWorkSpaceSettings.readWorkspaceSettings(workspace_folder);

		final EclipseProjectInfo libsProject = workspace_settings.getProjectInfo("libs");
		final File libsProjectFolder = libsProject.getProjectPath();

		final File assets_folder = libsProjectFolder.child("assets");
		final File bank_folder = assets_folder.child("bank-lib");
		final File targetTank = bank_folder.child("tank-0");
		L.d("targetTank", targetTank);

		final ChildrenList jarFolders = libsProjectFolder
			.listDirectChildren(file -> !(file.getName().equals("assets") || file.getName().startsWith(".")));
		jarFolders.print("jarFolders");

		final File redReporterJarsFilder = jarFolders.findChild("red-reporter");

		final List<File> reporterRequiredJars = redReporterJarsFilder.listAllChildren().filter(file -> file.extensionIs("jar"));

		final List<File> jarsToPack = Collections.newList();
		jarsToPack.addAll(reporterRequiredJars);
		jarsToPack.print("jarsToPack");

// targetTank.listAllChildren().print("content");

// final RelativePath inputJarRelative = JUtils.newRelativePath("/aws/1.11.42/lib/aws-java-sdk-1.11.42.jar");
// final File inputJar = libsProjectFolder.proceed(inputJarRelative);
// L.d("inputJar", inputJar);
		final JarRanaPackerConfig cfg = JarRanaPacker.newConfig();
		cfg.setOutputTank(targetTank);
// cfg.setPackageName();
		cfg.addInputJars(jarsToPack);

		JarRanaPacker.pack(cfg);

// classes.print("classes");

	}

}
