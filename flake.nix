{
  description = "Dev shell for fm-rpg-backend";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
  };

  outputs = { self, nixpkgs }: {
    devShells.x86_64-linux.default = let
      pkgs = import nixpkgs {
        system = "x86_64-linux";
      };
    in pkgs.mkShell {
      buildInputs = [
        pkgs.openjdk21
        pkgs.gradle
      ];

      shellHook = ''
        echo "Welcome to the fm-rpg-backend dev shell!"
      '';
    };
  };
}
