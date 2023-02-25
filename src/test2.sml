f1: mov EAX 6
    mov EBX 1
f2: mov ECX 1
f3: mul EBX EAX
    sub EAX ECX
    jnz EAX f3
    out EBX