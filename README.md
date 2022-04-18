# pwValidator

## Api End Point
/validator/pwValidate


## Properties 
pw.length.min=5

pw.length.max=12

pw.type=LOWERCASE,NUMBER

pw.type.min.count={NUMBER: 1, LOWERCASE: 1}


## Password Validations

All Validation should implements `Validation` class which can implement `isValid(String str)` and  `getErrorMessage()` and `getSuccessMessage()` depends on requirements.


* LengthValidation validates if the length of the input meets the min & max requirements.

>`getErrorMessage()` will return `input length is invalid.` if the length is shorter or longer than setting.

>`getErrorMessage()` will return `Properties setting is invalid in length.` if the setting min is larger than max.

>`getSuccessMessage()` will return `LengthValidation is passed.`


* PatternValidation validates if the input meets the required pattern and the min/max number needed.

>`getErrorMessage()` will return `input doesn't match to type.` if the input contains other types than setting.

>`getErrorMessage()` will return `input doesn't contain specific type.` if the input doesn't contain a required type.

>`getErrorMessage()` will return `input doesn't meet the count of types.`  

>`getErrorMessage()` will return `input is over the the count of types.`
>`getSuccessMessage()` will return `PatternValidation is passed.`


* SequenceValidation validates if the input has repeated continuously,=.

>`getErrorMessage()` will return `input is repeated with sequence.`
>`getSuccessMessage()` will return `SequenceValidation is passed.`


## Request & Response format

e.g.

Request:

```
{
  "password":"abcde2abcde2"
}
```

Response:

```
{
    "message": [
        "LengthValidation is passed.",
        "PatternValidation is passed.",
        "input is repeated with sequence."
    ]
}
```


## Test Cases

Some tests cases are provided to check if the validations can meet the requirement:

https://github.com/liq19ch/pwValidator/blob/master/src/test/validatiojnTestCases
