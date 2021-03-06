module.exports = {
  transform: {
    '^.+\\.tsx?$': 'ts-jest'
  },
  rootDir: '../../../',
  coverageDirectory: '<rootDir>/build/test-results/',
  testMatch: ['<rootDir>/src/test/javascript/spec/**/+(*.)+(spec.ts?(x))'],
  moduleFileExtensions: ['ts', 'tsx', 'js', 'jsx', 'json', 'node'],
  moduleNameMapper: {
    'app/(.*)': '<rootDir>/src/main/webapp/app/$1',
    '\\.(css|scss)$': 'identity-obj-proxy'
  },
  reporters: [
    'default'
  ],
  testResultsProcessor: 'jest-sonar-reporter',
  testPathIgnorePatterns: [
    '<rootDir>/node_modules/',
    '<rootDir>/src/test/javascript/spec/app/modules/account/sessions/sessions.reducer.spec.ts'
  ],
  setupFiles: [
    '<rootDir>/src/test/javascript/spec/enzyme-setup.ts',
    '<rootDir>/src/test/javascript/spec/storage-mock.ts'
  ],
  globals: {
    'ts-jest': {
      tsConfigFile: './tsconfig.test.json'
    }
  }
};
