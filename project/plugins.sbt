logLevel := Level.Warn

//resolvers ++= Seq(
//  Resolver.url("scoverag,e-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns),
//  "jgit-repo" at "http://download.eclipse.org/jgit/maven"
//)

// https://github.com/sbt/sbt-native-packager
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.5")

// to format scala source code
// https://github.com/sbt/sbt-scalariform
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

// Check whether dependencies can be updated (sbt dependencyUpdates)
// https://github.com/rtimush/sbt-updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.0")

// Analyze the dependency graph of the project, identify sources of conflict
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

// Automate insertion of license headers
addSbtPlugin("de.heikoseeberger"    % "sbt-header"              % "1.5.1")

// Enables PGP when publishing
addSbtPlugin("com.jsuereth"         % "sbt-pgp"                 % "1.0.0")

// To customize the release process
addSbtPlugin("com.github.gseitz"    % "sbt-release"             % "1.0.3")

// tut allows us to create documentation with compiled code samples
addSbtPlugin("org.tpolecat"         % "tut-plugin"              % "0.4.8")

//addSbtPlugin("org.scalastyle"       %% "scalastyle-sbt-plugin"  % "0.8.0")
//addSbtPlugin("org.scoverage"        % "sbt-scoverage"           % "1.3.5")
//addSbtPlugin("com.eed3si9n"         % "sbt-unidoc"              % "0.3.3")
//addSbtPlugin("com.github.tkawachi"  % "sbt-doctest"             % "0.3.5")
//addSbtPlugin("com.typesafe.sbt"     % "sbt-site"                % "1.0.0")
//addSbtPlugin("com.typesafe.sbt"     % "sbt-ghpages"             % "0.5.4")
//addSbtPlugin("org.xerial.sbt"       % "sbt-sonatype"            % "1.1")