import math


_CHAR_TABLE = [
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    '+', '/'
]

def encode(value: bytes) -> str:
    encodedValue = ""
        
    wholeLen = len(value) // 3
    bigNumber = 0
    for i in range(0, wholeLen, 3):
        bigNumber = value[i] << 16 | value[i + 1] << 8 | value[i + 2]

        encodedValue += (
            _CHAR_TABLE[bigNumber >> 18 & 0x3f]
            + _CHAR_TABLE[bigNumber >> 12 & 0x3f]
            + _CHAR_TABLE[bigNumber >> 6 & 0x3f]
            + _CHAR_TABLE[bigNumber & 0x3f]
        )

    leftIndexes = math.ceil(len(value) / 3 - wholeLen)
    if leftIndexes == 0:
        return encodedValue
    
    bigNumber = 0
    for i in range(leftIndexes):
        bigNumber |= value[leftIndexes + wholeLen]<<8

    bigNumber <<= 8 * (2 - leftIndexes)

    encodedValue += _CHAR_TABLE[bigNumber >> 18 & 0x3f]
    if leftIndexes == 1:
        encodedValue += _CHAR_TABLE[bigNumber >> 12 & 0x3f] + "=="
    else:
        encodedValue += _CHAR_TABLE[bigNumber >> 12 & 0x3f]
        encodedValue += _CHAR_TABLE[bigNumber >> 6 & 0x3f] + "="

    return encodedValue

if __name__ == "__main__":
    value = "Man"
    encodedValue = encode(value.encode())
    assert encodedValue == "TWFu"

    value = "Mann"
    encodedValue = encode(value.encode())
    assert encodedValue == "TWFubg=="

    value = "Ma"
    encodedValue = encode(value.encode())
    assert encodedValue == "TWE="