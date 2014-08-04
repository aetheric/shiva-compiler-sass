require 'rubygems'
require 'compass'

frameworks = Dir.new(Compass::Frameworks::DEFAULT_FRAMEWORKS_PATH).path

Compass::Frameworks.register_directory(File.join(frameworks, 'compass'))
Compass::Frameworks.register_directory(File.join(frameworks, 'blueprint'))

Compass.add_project_configuration configLocation
Compass.configure_sass_plugin!
