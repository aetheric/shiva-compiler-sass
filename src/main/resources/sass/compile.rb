Dir.chdir(File.dirname(configLocation)) do
  Compass.compiler.run
end