# Wrappit

![CodeRabbit Pull Request Reviews](https://img.shields.io/coderabbit/prs/github/mustafa-zidan/wrappit?utm_source=oss&utm_medium=github&utm_campaign=mustafa-zidan%2Fwrappit&labelColor=171717&color=FF570A&link=https%3A%2F%2Fcoderabbit.ai&label=CodeRabbit+Reviews)

This repository is a demonstration of how to use CodeRabbit, an AI code review tool that helps you maintain high-quality code in your projects.


Here's a complete breakdown of what you can do with it — from simple use cases to very complex, fine-tuned workflows.

## Table of Contents
- [Basic Usage](#basic-usage)
- [Configuration File](#configuration-file)
- [Advanced Features](#advanced-features)
  - [Prompt Tuning](#1-prompt-tuning-custom-instructions)
  - [Test Enforcement](#2-test-enforcement)
  - [Security Rules](#3-security-rules)
  - [Directory-specific Configs](#4-directory-specific-configs)
- [Complex Scenario Example](#complex-scenario-example)
- [GitHub Actions Integration](#github-actions-integration)
- [Monitor & Adjust](#monitor--adjust)

## Basic Usage

Once installed via [https://github.com/apps/coderabbit-ai](https://github.com/apps/coderabbit-ai), CodeRabbit automatically reviews new pull requests.

It comments on:
- Bad code patterns
- Potential bugs
- Style issues
- Missing tests
- Poor naming
- Unused code

No configuration is required for this out-of-the-box behavior.

## Configuration File

To customize CodeRabbit behavior, create a file in your repo root:

```yaml
# .coderabbit/config.yml
enabled: true

review:
  languages: ["kotlin", "java"]
  review_scope: "full"          # options: full, diff-only
  max_comments: 15

tone: "constructive"            # options: friendly, professional, direct, constructive

rules:
  - avoid_println
  - enforce_unit_tests
  - no_hardcoded_secrets
```

## Advanced Features

### 1. Prompt Tuning (Custom Instructions)

You can instruct CodeRabbit to follow custom logic:

```yaml
instructions:
  - "If a new endpoint is added, check for input validation."
  - "Point out if a REST controller lacks a test."
  - "Ensure all services have logging via our Logger.kt pattern."
```

### 2. Test Enforcement

```yaml
rules:
  - name: enforce_test_coverage
    threshold: 80
```

CodeRabbit will flag changes that reduce test coverage below 80%.

### 3. Security Rules

```yaml
rules:
  - no_hardcoded_secrets
  - no_sql_injection_patterns
  - no_eval_usage
```

### 4. Directory-specific Configs

You can use different configs per folder if using monorepos:

```
/backend/.coderabbit/config.yml
/frontend/.coderabbit/config.yml
```

## Complex Scenario Example

You want CodeRabbit to:
- Review full Kotlin PRs for REST endpoints and warn if:
  - There's no test for each new controller
  - Logging is missing
  - API response models are undocumented
- Enforce no logging of sensitive data
- Limit AI chatter to 10 top comments per PR
- Respond constructively

```yaml
enabled: true

tone: "constructive"

review:
  languages: ["kotlin"]
  review_scope: "full"
  max_comments: 10

instructions:
  - "If a new REST endpoint is created, verify there's a corresponding test case."
  - "Warn about any controller method that lacks input validation or exception handling."
  - "Check for usage of our structured logger (LoggerFactory.getLogger())."
  - "Flag any logging of passwords or access tokens."
  - "Ensure all public models used in REST responses have documentation comments."

rules:
  - no_hardcoded_secrets
  - avoid_plain_password_logging
  - enforce_test_coverage
```

## GitHub Actions Integration

You can also trigger CodeRabbit from a CI pipeline:

```yaml
name: Code Review with CodeRabbit

on:
  pull_request:
    types: [opened, synchronize]

jobs:
  review:
    runs-on: ubuntu-latest
    steps:
      - name: Run CodeRabbit
        uses: coderabbit-ai/review-action@latest
```

## Monitor & Adjust

You can audit CodeRabbit's performance by:
- Reviewing its comments on PRs
- Checking coverage and testing gaps
- Adjusting config to reduce noise or increase strictness
