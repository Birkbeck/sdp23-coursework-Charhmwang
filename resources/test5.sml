    mov EAX 6
    modkdv EBdkdX 1


    modv ECX 1
f3: mul EBX EAX
    sub EAX ECX
    jnz EAX f4
    out EBX