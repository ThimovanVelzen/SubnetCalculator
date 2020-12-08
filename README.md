# SubnetCalculator
### input

Program will ask for a IP-range (prefix included)\
\
`ip/prefix` : `192.168.0.2/20`

### output

Program will print several tables to the screen

```
SUBNET 0             | DECIMAL              | BINARY 
SubnetMask:          | 255.255.254.0/23     | 11111111.11111111.11111110.00000000
Network Address:     | 81.208.80.0          | 01010001.11010000.01010000.00000000
Broadcast Address:   | 81.208.81.255        | 01010001.11010000.01010001.11111111
First Router IP:     | 81.208.80.1          | 01010001.11010000.01010000.00000001
First Host IP:       | 81.208.80.2          | 01010001.11010000.01010000.00000010
Last Host IP:        | 81.208.81.254        | 01010001.11010000.01010001.11111110
IP amount: 512

SUBNET 1             | DECIMAL              | BINARY 
SubnetMask:          | 255.255.254.0/23     | 11111111.11111111.11111110.00000000
Network Address:     | 81.208.82.0          | 01010001.11010000.01010010.00000000
Broadcast Address:   | 81.208.83.255        | 01010001.11010000.01010011.11111111
First Router IP:     | 81.208.82.1          | 01010001.11010000.01010010.00000001
First Host IP:       | 81.208.82.2          | 01010001.11010000.01010010.00000010
Last Host IP:        | 81.208.83.254        | 01010001.11010000.01010011.11111110
IP amount: 512
```

**SubnetMask** -> Same for all subnets\
**Network Address** -> unique indentifier for each subnet - first IP-Address in the subnet\
**Broadcast Address** -> last IP-Address in the subnet\
**First Router IP** -> Standard IP-Address for router in subnet - default gateway\
**First Host IP** -> First IP-Address allowed to give to a host\
**Last Host IP** -> Last IP-Address allowed to give to a host\
**IP amount** -> all the IP-Addresses in the subnet (included Network Address and Broadcast Address)
