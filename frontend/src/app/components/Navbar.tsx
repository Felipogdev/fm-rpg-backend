'use client';
import Link from 'next/link';
import { useState } from 'react';
import { Menu, X } from 'lucide-react';
import { Button } from '@/components/ui/button';
import LoginButton from "@/app/components/LoginButton";


const Navbar = () => {
    const [isOpen, setIsOpen] = useState(false);

    const toggleMenu = () => setIsOpen(!isOpen);

    return (
        <nav className="bg-black shadow-md fixed top-0 left-0 right-0 z-50">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-between h-16">
                    {/* Logo */}
                    <Link href="/" className="text-2xl font-bold text-red-800">
                        FMSHEET
                    </Link>

                    {/* Desktop Menu */}
                  <div className="hidden md:flex justify-center space-x-6 flex-1">
                      <Link href="/feiticeiros" className="text-red-800 hover:text-pink-50">Feiticeiros</Link>
                      <Link href="/campanhas" className="text-red-800 hover:text-pink-50">Campanhas</Link>
                      <Link href="/maldicoes" className="text-red-800 hover:text-pink-50">Maldições</Link>
                  </div>
                    <div className="hidden md:flex">
                       <LoginButton />

                    </div>
                    {/* Mobile Menu Button */}
                    <div className="md:hidden">
                        <button onClick={toggleMenu} className="text-gray-700">
                            {isOpen ? <X size={24} /> : <Menu size={24} />}
                        </button>
                    </div>
                </div>
            </div>

            {/* Mobile Dropdown Menu */}
            {isOpen && (
                <div className="md:hidden bg-black  shadow-md">
                    <div className="px-2 pt-2 pb-3 space-y-1">
                        <LoginButton />
                        <Link href="/feiticeiros" className="block px-3 py-2 text-red-800 hover:text-pink-50">Feiticeiros</Link>
                        <Link href="/campanhas" className=" block px-3 py-2 text-red-800 hover:text-pink-50">Campanhas</Link>
                        <Link href="/maldicoes" className="block px-3 py-2 text-red-800 hover:text-pink-50">Maldições</Link>
                    </div>
                </div>
            )}
        </nav>
    );
};

export default Navbar;
