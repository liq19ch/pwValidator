# pwValidator

## Api End Point
/validator/pwValidate


## Properties 
pw.rule.length.min=5

pw.rule.length.max=12

pw.rule.type=LOWERCASE,NUMBER

pw.rule.type.min.count={NUMBER: 1, LOWERCASE: 1}

`PwValidationSetting` implements `Setting` and will read the properties file.

`PatternType` is an Enum to define type.


## Password Validations

All Validation should extends `Validation` class which can implement `isValid(String str)` and  `getErrorMsg()` depends on requirements and share `isEmpty(String str)`.


* EmptyValidation validates if the input is empty or null.

>`getErrorMsg()` will return `input is empty`


* LengthValidation validates if the length of the input meets the min & max requirements.

>`getErrorMsg()` will return `input length is invalid.` if the length is shorter or longer than setting.

>`getErrorMsg()` will return `Properties setting is invalid in length.` if the setting min is larger than max.


* PatternValidation validates if the input meets the required pattern and the min/max number needed.

>`getErrorMsg()` will return `input doesn't match to type.` if the input contains other types than setting.

>`getErrorMsg()` will return `input doesn't contain specific type.` if the input doesn't contain a required type.

>`getErrorMsg()` will return `input doesn't meet the count of types.`  

>`getErrorMsg()` will return `input is over the the count of types.`


* SequenceValidation validates if the input has repeated continuously,=.

>`getErrorMsg()` will return `input is repeated with sequence.`


Validations can be added in the list of `Validator` class. Use 'Validator` to validate input.


## Test Cases

Some tests cases are provided to check if the validations can meet the requirement:

https://github.com/liq19ch/pwValidator/blob/master/src/test/validatiojnTestCases
