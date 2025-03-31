#!/usr/bin/env python3
from scapy.all import *

def spoof(pkt):
    if ICMP in pkt and pkt[ICMP].type == 8:  # Echo Request
        print(f"[+] ICMP Echo Request Detected from {pkt[IP].src} to {pkt[IP].dst}")

        ip = IP(src=pkt[IP].dst, dst=pkt[IP].src)
        icmp = ICMP(type=0, id=pkt[ICMP].id, seq=pkt[ICMP].seq)
        data = pkt[Raw].load if Raw in pkt else b''
        spoofed_pkt = ip / icmp / data

        send(spoofed_pkt, verbose=0)
        print(f"[+] Sent Spoofed ICMP Echo Reply to {pkt[IP].src}")

print("[*] Sniffing for ICMP Echo Requests...")
sniff(filter="icmp", prn=spoof, store=0)
