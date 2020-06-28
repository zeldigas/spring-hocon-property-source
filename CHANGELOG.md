# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.4.0] - 2020-05-25 
### Added
- `@HoconPropertySource` annotation. You can use it to load HOCON files to env,
just like you load `*.properties` files with Spring's `@PropertySource`
### Changed
- Project is built against SpringBoot 2.2. Dependency still uses `provided` scope
 so it should not be a problem to use any version you prefer though, as Spring's API is stable
- Hocon dependency bumped to 1.4.0 
### Fixed
- Empty lists now added to processed properties, so you can override non-empty list with empty.

Kudos to [FingolfinTEK](https://github.com/FingolfinTEK) for empty list fix and `@HoconPropertySource` feature!

## [0.3.0] - 2020-05-25
### Added
- Support for org.springframework.boot.origin.TextResourceOrigin
### Fixed
- Closing input stream after reading HOCON file

## [0.2.2] - 2020-04-26
### Added
- Changelog file added to repo
### Updated
- License file (years interval fixed)
### Fixed
- Specifying file encoding explicitly to adhere to HOCON spec (#2)

## [0.2.1] - 2019-03-18
### Changed
- Spring Boot 2 as a target version to support.
- Java 8 used as a compilation target

## [0.1] - 2016-06-23
### Added
-  Initial support for hocon config files as spring property source

[0.4.0]: https://github.com/zeldigas/spring-hocon-property-source/compare/0.3.0...0.4.0
[0.3.0]: https://github.com/zeldigas/spring-hocon-property-source/compare/0.2.2...0.3.0
[0.2.2]: https://github.com/zeldigas/spring-hocon-property-source/compare/0.2.1...0.2.2
[0.2.1]: https://github.com/zeldigas/spring-hocon-property-source/compare/0.1...0.2.1
[0.1]: https://github.com/zeldigas/spring-hocon-property-source/releases/tag/0.1  