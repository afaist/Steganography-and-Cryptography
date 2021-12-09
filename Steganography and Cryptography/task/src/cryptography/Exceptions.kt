package cryptography

class ExceptionNotEnoughSpace: Exception("The input image is not large enough to hold this message")
class ExceptionNotFoundMessage: Exception("A message was not found in this file")
class ExceptionBadSymbolInString: Exception("Bad symbol in string!!")