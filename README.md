# WxSimpleConfig Package

WxSimpleConfig package is enhancing he configuration automation for Integration Server or MSR. It is useful for external configuration use cases where the user prefers to store the configuration in source control rather than in the config folder.

## Declaration concept

The primitives are built on a "declaration" concept. A declaration translates in the verification if a certain configuration exists or not and its creation in case it does not exist. This allows for flexibility for the classical IS use case, where the administrators may change the configuration using the user interface.

## Compatibility

The package should be compatible with all IS and MSR versions, but it hasn't been tested against all versions.
