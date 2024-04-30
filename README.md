## Usage

```bash
$ docker compose up -d
$ make run
```

```bash
$ curl -d "https://nixos.org" -X POST http://localhost:3000/normal-url
PicFZR
$ curl -X GET http://localhost:3000/PicFZR
https://nixos.org
$ curl -X PUT -d "https://search.nixos.org/packages" http://localhost:3000/short-url/PicFZR
$ curl -X GET http://localhost:3000/PicFZR
https://search.nixos.org/packages
$ curl -X DELETE http://localhost:3000/short-url/PicFZR
```

## License

Copyright © 2024 Ivan Berlin

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
